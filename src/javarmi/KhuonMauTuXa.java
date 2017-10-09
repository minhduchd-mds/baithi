/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author daolinh
 */
public interface KhuonMauTuXa extends Remote{
    
    public float chuyendoi(float kilo) throws RemoteException;
}
