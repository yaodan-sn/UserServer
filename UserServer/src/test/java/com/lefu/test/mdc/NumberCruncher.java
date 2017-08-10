package com.lefu.test.mdc;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NumberCruncher extends Remote {
  /**
   * Factor a positive integer number and return its
   * distinct factor's as an integer array.
   * */
  int[] factor(int number) throws RemoteException;
}