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

public class DownloadJob {

    private int downloadSize;
    private int downloadSizeRemaining;
    private int timeRequested;
    private boolean isPremium;
    private int id;
    /**
     * Download Job default constructor
     */
    public DownloadJob() {

    }
    /**
     * Download Job constructor
     * @param id
     * value for set id number 
     * @param ds
     * value for set downloadSize number
     * @param rt
     * value for set timeRequested number
     * @param isP
     * boolean for set isPremium
     * set downloadSizeRemaining equal to downloadSize
     */
    public DownloadJob(int id, int ds, int rt, boolean isP) {
        this.id = id;
        downloadSize = ds;
        timeRequested = rt;
        isPremium = isP;
        downloadSizeRemaining = downloadSize;
    }
    /**
     * Get downloadSize
     * @return
     * value of downloadSize
     */
    public int getDownloadSize() {
        return downloadSize;
    }
    /**
     * Get timeRequested
     * @return
     * value of timeRequested
     */
    public int getTimeRequested() {
        return timeRequested;
    }
    /**
     * Set downloadSizeRemaining
     * @param rds
     * the value for set downloadSizeRemaining
     */
    public void setDownloadSizeRemaining(int rds) {
        downloadSizeRemaining = rds;
    }
    /**
     * Get downloadSizeRemaining
     * @return
     * value of downloadSizeRemaining
     */
    public int getDownloadSizeRemaining() {
        return downloadSizeRemaining;
    }
    /**
     * Print is Premium of Regular
     * @return
     * if isPremium is true, print Premium. Otherwise, print Regular
     */
    public String memberInfor() {
        if (isPremium) {
            return "Premium";
        } else {
            return "Regular";
        }
    }
    /**
     * Check if is Premium
     * @return
     * True, if is Premium. False, otherwise
     */
    public boolean isPremium(){
        return isPremium;
    }
    /**
     * Get ID number
     * @return
     * value of id number
     */
    public int getID() {
        return id;
    }
    /**
     * Print the information of current download job
     * @return
     * string of download job information
     */
    public String toString() {
        return "[#" + id + ":  " + downloadSize + "Mb total,  " + downloadSizeRemaining + "Mb remaining,  Request Time: " + timeRequested + ", " + memberInfor() + "]";
    }

}
