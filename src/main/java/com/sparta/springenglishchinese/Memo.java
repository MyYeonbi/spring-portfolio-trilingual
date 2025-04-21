package com.sparta.springenglishchinese;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@RequiredArgsConstructor
public class Memo {

  private String username;

  private String contents;



}

class Main {
  public static void main(String[] args) {
    Memo memo = new Memo();

    memo.setUsername("Yeonbi");
    System.out.println(memo.getUsername());
  }
}
