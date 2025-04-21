package com.sparta.springenglishchinese;


public class Memo {

  private String username;

  private String contents;


  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return this.username;
  }
}

class Main {
  public static void main(String[] args) {
    Memo memo = new Memo();
    memo.setUsername("Yeonbi");
    System.out.println(memo.getUsername());
  }
}
