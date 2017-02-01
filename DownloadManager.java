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

import java.util.Scanner;

public class DownloadManager {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DownloadScheduler scheduler = new DownloadScheduler();
        boolean isPass = false;
        System.out.println("Hello and welcome to the Download Scheduler.");
        System.out.println("");
        do {
            System.out.print("Please enter a number of servers: ");
            int numOfServ = input.nextInt();
            if (numOfServ > 0) {
                scheduler.setDownloadJobArraySize(numOfServ);
                isPass = true;
            }else
                System.out.println("Number of servers can not less than one.");
        } while (!isPass);
        isPass = false;
        do {
            System.out.print("Please enter a download speed: ");
            int dlSpeed = input.nextInt();
            if (dlSpeed > 0) {
                scheduler.setDownloadSpeed(dlSpeed);
                isPass = true;
            }else
                System.out.println("Download speed can not less than one.");
        } while (!isPass);
        isPass = false;
        do {
            System.out.print("Please enter a length of time: ");
            int lengthOfTime = input.nextInt();
            if (lengthOfTime > 0) {
                scheduler.setSimulationEndTime(lengthOfTime);
                isPass = true;
            }else
                System.out.println("Length of time can not less than one.");
        } while (!isPass);
        isPass = false;
        do {
            System.out.print("Please enter a probability of new premium job per timestep: ");
            double premiumJobTimestep = input.nextDouble();
            System.out.print("Please enter a probability of new regular job per timestep: ");
            double regularJobTimestep = input.nextDouble();
            if((premiumJobTimestep>=0.0&&premiumJobTimestep<=1.0) && (regularJobTimestep>=0.0&&regularJobTimestep<=1.0)){
                scheduler.setDownloadRandomizer(premiumJobTimestep, regularJobTimestep);
                isPass=true;
            }else
                System.out.println("Probability must between 0-1 inclusive.");
        } while (!isPass);
        System.out.print(scheduler.simulate());

    }
}
