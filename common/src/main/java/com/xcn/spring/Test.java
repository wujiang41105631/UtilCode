package com.xcn.spring;

public class Test {
   private Test(){

   }

   private  static class TestHelper{
        private static final Test testHelper = new Test();
   }

   public Test getHelper(){
       return TestHelper.testHelper;
   }



}






