/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dz.umab.chat.dskclient.client;

import dz.umab.chat.dskclient.GUI.MainFrame;

import javax.swing.*;
import java.util.concurrent.Callable;

/**
 * @author user
 */
public class AuthenticateTask<Boolean> implements Callable {

    String login = "", pass = "";
    ObserverInterface observer;

    public AuthenticateTask(String login, String pass, ObserverInterface observer) {
        this.login = login;
        this.pass = pass;
        this.observer = observer;
    }

    public Object call() {

        try {
            Thread.sleep(800);
        } catch (InterruptedException ex) {
            System.out.println("exception sleep");
        }
        int result = -1;
        if (MainFrame.client.creeConnexion()) {
            if (MainFrame.client.authentifier(login, pass)) {
                observer.setTitre(login);

                result = 1;
            } else {
                result = 0;
            }
        }

        SwingUtilities.invokeLater(new RunnableImpl(result));


        System.out.println("authentication task= " + result);
        return 0;
    }

    private class RunnableImpl implements Runnable {

        private final int result;

        public RunnableImpl(int result) {
            this.result = result;
        }

        public void run() {
            observer.result(0, result);
        }
    }
}
