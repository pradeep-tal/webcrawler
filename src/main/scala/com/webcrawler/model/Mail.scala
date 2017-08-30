package com.webcrawler.model

class Mail(var messageId: String, var from: String,var subject: String, var date: String, var messageBody: String) {

  def this() {
    this("", "", "", "", "");
  }

  override def toString(): String = {

    "Message-Id= " + messageId + ",From =" + from + ",Subject= " + subject + ",Date =" + date

  }

}