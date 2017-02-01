/**
 * Xia Lin
 * 110732381
 * xia.lin@stonybrook.edu
 * Assignment 4
 * CSE214-01
 * Charles Chen
 * Shilpi Bhattacharyya
 */
package homework4;

public class DownloadScheduler {

    private DownloadQueue regularQ;
    private DownloadQueue premiumQ;
    private DownloadRandomizer randomizer;
    private int currentTime;
    private int simulationEndTime;
    private DownloadJob[] currentJobs;
    private int downloadSpeed;
    private int idNum = 1;
    private boolean isPutPremiumJob = false, isPutRegularJob = false, isPut = false;

    /**
     * Download scheduler constructor initialized regularQ and premiumQ set
     * currentTime to zero
     */
    public DownloadScheduler() {
        regularQ = new DownloadQueue();
        premiumQ = new DownloadQueue();
        currentTime = 0;
    }

    /**
     * Set download Speed
     *
     * @param sd value for downloadSpeed
     */
    public void setDownloadSpeed(int sd) {
        downloadSpeed = sd;
    }

    /**
     * Set simulation end time
     *
     * @param sset value for simulationEndTime
     */
    public void setSimulationEndTime(int sset) {
        simulationEndTime = sset;
    }

    /**
     * Set downloadJob array size
     *
     * @param size array size value
     */
    public void setDownloadJobArraySize(int size) {
        currentJobs = new DownloadJob[size];
    }

    /**
     * Set download randomizer
     *
     * @param p the probability for premium
     * @param r the probability for regular
     */
    public void setDownloadRandomizer(double p, double r) {
        randomizer = new DownloadRandomizer(p, r);
    }

    /**
     * This method creates a simulation based on the user input
     *
     * @return the string of all output
     */
    public String simulate() {
        int jobSer = 0, preJobSer = 0, regJobSer = 0;
        int dataSer = 0, preDataSer = 0, regDataSer = 0;
        int avePre = 0, aveReg = 0;
        double countPre = 0.0, countReg = 0.0;
        String printFinished = "";
        String printReturn = "---------------------------------Simulation Starting--------------------------------\n";
        while (currentTime != simulationEndTime + 1) {
            DownloadJob newRegularJob = new DownloadJob();
            DownloadJob newPremiumJob = new DownloadJob();
            int randomPremium = randomizer.getPremium();
            int randomRegular = randomizer.getRegular();
            printReturn += "Timestep " + currentTime + ":\n";
            printReturn += "\tNew Regular Job: ";
            if (randomRegular == -1) {
                printReturn += "n/a\n";
            } else {
                newRegularJob = new DownloadJob(idNum++, randomRegular, currentTime, false);
                printReturn += "Job#" + newRegularJob.getID() + ": Size: " + newRegularJob.getDownloadSize() + "Mb\n";
            }
            printReturn += "\tNew Premium Job: ";
            if (randomPremium == -1) {
                printReturn += "n/a\n";
            } else {
                newPremiumJob = new DownloadJob(idNum++, randomPremium, currentTime, true);
                printReturn += "Job#" + newPremiumJob.getID() + ": Size: " + newPremiumJob.getDownloadSize() + "Mb\n";
            }

            if (randomPremium != -1 && !isPutPremiumJob) {
                premiumQ.enqueued(newPremiumJob);
            }
            if (randomRegular != -1 && !isPutRegularJob) {
                regularQ.enqueued(newRegularJob);
            }

            for (int i = 0; i < currentJobs.length; i++) {
                if (currentJobs[i] == null) {
                    if (!isPutPremiumJob) {
                        if (randomPremium != -1) {
                            try {
                                currentJobs[i] = premiumQ.dequeued();
                                isPut = true;
                                isPutPremiumJob = true;
                            } catch (EmptyQueueException ex) {
                            }
                        }
                    }
                    if (!isPut) {
                        if (randomRegular != -1) {
                            try {
                                currentJobs[i] = regularQ.dequeued();
                                isPutRegularJob = true;
                                break;
                            } catch (EmptyQueueException ex) {
                            }
                        } else {
                            break;
                        }
                    }
                    isPut = false;
                }
            }
            for (int i = 0; i < currentJobs.length; i++) {
                if (currentJobs[i] == null) {
                } else if (currentJobs[i].getDownloadSizeRemaining() <= 0) {
                    jobSer++;
                    dataSer += currentJobs[i].getDownloadSize();
                    if (currentJobs[i].isPremium()) {
                        preJobSer++;
                        countPre++;
                        preDataSer += currentJobs[i].getDownloadSize();
                        avePre += currentTime - currentJobs[i].getTimeRequested();
                    } else {
                        regJobSer++;
                        countReg++;
                        regDataSer += currentJobs[i].getDownloadSize();
                        aveReg += currentTime - currentJobs[i].getTimeRequested();
                    }
                    printFinished += "Job " + currentJobs[i].getID() + " finished, " + currentJobs[i].memberInfor() + " job. "
                            + currentJobs[i].getDownloadSize() + "Mb served, Total wait time: " + (currentTime - currentJobs[i].getTimeRequested()) + "\n";
                    if (!premiumQ.isEmpty()) {
                        try {
                            currentJobs[i] = premiumQ.dequeued();
                        } catch (EmptyQueueException ex) {
                        }
                    } else if (!regularQ.isEmpty()) {
                        try {
                            currentJobs[i] = regularQ.dequeued();
                        } catch (EmptyQueueException ex) {
                        }
                    } else {
                        currentJobs[i] = null;
                    }
                }
            }

            printReturn += "\tRegularQueue: ";
            if (regularQ.isEmpty()) {
                printReturn += "empty\n";
            } else {
                printReturn += regularQ.toString() + "\n";
            }
            printReturn += "\tPremiumQueue: ";
            if (premiumQ.isEmpty()) {
                printReturn += "empty\n";
            } else {
                printReturn += premiumQ.toString() + "\n";
            }
            int count = 1;
            for (int i = 0; i < currentJobs.length; i++) {
                printReturn += "\tServer " + count++ + ": ";
                if (currentJobs[i] == null) {
                    printReturn += "idle\n";
                } else {
                    printReturn += currentJobs[i].toString() + "\n";
                    currentJobs[i].setDownloadSizeRemaining(currentJobs[i].getDownloadSizeRemaining() - downloadSpeed);
                }
            }
            if (!printFinished.equals("")) {
                printReturn += printFinished;
                printFinished = "";
            }
            printReturn += "-----------------------------------------------------------------------------------\n";
            currentTime++;
            isPutPremiumJob = false;
            isPutRegularJob = false;
            isPut = false;
        }

        printReturn += "Simulation Ended:\n";
        printReturn += "\tTotal Jobs Served: " + jobSer + "\n";
        printReturn += "\tTotal Premium Jobs Served: " + preJobSer + "\n";
        printReturn += "\tTotal Regular Jobs Served: " + regJobSer + "\n";
        printReturn += "\tTotal Data Served: " + dataSer + " Mb\n";
        printReturn += "\tTotal Premium Data Served: " + preDataSer + " Mb\n";
        printReturn += "\tTotal Regular Data Served: " + regDataSer + " Mb\n";
        if (countPre == 0 && countReg == 0) {
            printReturn += "\tThere are no any jobs been served.\n";
        } else if (countReg == 0) {
            double avePreFinal = avePre / countPre;
            printReturn += "\tAverage Premium Wait Time: " + avePreFinal + "\n";
            printReturn += "\tThere are no Regular jobs been served.\n";
        } else if (countPre == 0) {
            double aveRegFinal = aveReg / countReg;
            printReturn += "\tAverage Regular Wait Time: " + aveRegFinal + "\n";
            printReturn += "\tThere are no Premium jobs been served.\n";
        } else {
            double avePreFinal = avePre / countPre;
            double aveRegFinal = aveReg / countReg;
            printReturn += "\tAverage Premium Wait Time: " + avePreFinal + "\n";
            printReturn += "\tAverage Regular Wait Time: " + aveRegFinal + "\n";
        }
        printReturn += "-------------------------Thank You For Running the Simulator------------------------";
        return printReturn;
    }
}
