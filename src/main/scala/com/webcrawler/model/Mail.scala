package com.webcrawler.model

class Mail(val messageId: String, val from: String,val subject: String, val date: String, val messageBody: String) {

  def this() {
    this("", "", "", "", "");
  }

  override def toString(): String = {

    "Message-Id= " + messageId + ",From =" + from + ",Subject= " + subject + ",Date =" + date

  }

}