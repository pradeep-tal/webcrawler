package com.webcrawler

object Regex extends App {

  import scala.util.matching.Regex

  val keyValPattern: Regex = "([0-9a-zA-Z-#() ]+): ([0-9a-zA-Z-#();/ ]+)".r

  val keyValPattern2: Regex = "(background-color|background-image|background-position): ([0-9a-zA-Z-#() ]+)".r

  val keyValMails: Regex = "(From|Date|Message-ID|Subject): ([0-9a-zA-Z-#()<>\"\"@.,: ]+)".r

  val keyValMails1: Regex = "(^To):$ ([0-9a-zA-Z-#()<>\"\"@.,: ]+)".r

  val input: String = """From users-return-142269-apmail-maven-users-archive=maven.apache.org@maven.apache.org  Sun Jul  2 09:22:10 2017\nReturn-Path: <users-return-142269-apmail-maven-users-archive=maven.apache.org@maven.apache.org>\nX-Original-To: apmail-maven-users-archive@www.apache.org\nDelivered-To: apmail-maven-users-archive@www.apache.org\nReceived: from mail.apache.org (hermes.apache.org [140.211.11.3])\n\tby minotaur.apache.org (Postfix) with SMTP id 8DA811A8BD\n\tfor <apmail-maven-users-archive@www.apache.org>; Sun,  2 Jul 2017 09:22:10 +0000 (UTC)\nReceived: (qmail 76453 invoked by uid 500); 2 Jul 2017 09:22:09 -0000\nDelivered-To: apmail-maven-users-archive@maven.apache.org\nReceived: (qmail 76375 invoked by uid 500); 2 Jul 2017 09:22:09 -0000\nMailing-List: contact users-help@maven.apache.org; run by ezmlm\nPrecedence: bulk\nList-Unsubscribe: <mailto:users-unsubscribe@maven.apache.org>\nList-Help: <mailto:users-help@maven.apache.org>\nList-Post: <mailto:users@maven.apache.org>\nList-Id: \"Maven Users List\" <users.maven.apache.org>\nReply-To: \"Maven Users List\" <users@maven.apache.org>\nDelivered-To: mailing list users@maven.apache.org\nReceived: (qmail 76364 invoked by uid 99); 2 Jul 2017 09:22:08 -0000\nReceived: from pnap-us-west-generic-nat.apache.org (HELO spamd2-us-west.apache.org) (209.188.14.142)\n    by apache.org (qpsmtpd/0.29) with ESMTP; Sun, 02 Jul 2017 09:22:08 +0000\nReceived: from localhost (localhost [127.0.0.1])\n\tby spamd2-us-west.apache.org (ASF Mail Server at spamd2-us-west.apache.org) with ESMTP id 349C01A08DF\n\tfor <users@maven.apache.org>; Sun,  2 Jul 2017 09:22:08 +0000 (UTC)\nX-Virus-Scanned: Debian amavisd-new at spamd2-us-west.apache.org\nX-Spam-Flag: NO\nX-Spam-Score: 0.479\nX-Spam-Level:\nX-Spam-Status: No, score=0.479 tagged_above=-999 required=6.31\n\ttests=[RCVD_IN_DNSWL_NONE=-0.0001, RCVD_IN_MSPIKE_H3=-0.01,\n\tRCVD_IN_MSPIKE_WL=-0.01, RCVD_IN_SORBS_SPAM=0.5, SPF_PASS=-0.001]\n\tautolearn=disabled\nReceived: from mx1-lw-eu.apache.org ([10.40.0.8])\n\tby localhost (spamd2-us-west.apache.org [10.40.0.9]) (amavisd-new, port 10024)\n\twith ESMTP id Hy7Dp9KoZS_M for <users@maven.apache.org>;\n\tSun,  2 Jul 2017 09:22:07 +0000 (UTC)\nReceived: from mout.gmx.net (mout.gmx.net [212.227.15.19])\n\tby mx1-lw-eu.apache.org (ASF Mail Server at mx1-lw-eu.apache.org) with ESMTPS id B7DE65FE5F\n\tfor <users@maven.apache.org>; Sun,  2 Jul 2017 09:22:06 +0000 (UTC)\nReceived: from Karl-Heinzs-MacBook-Pro.local ([217.247.12.26]) by mail.gmx.com\n (mrgmx002 [212.227.17.190]) with ESMTPSA (Nemesis) id\n 0MVe87-1dE8Hb2BxP-00Z19v; Sun, 02 Jul 2017 11:22:04 +0200\nReply-To: info@soebes.de\nSubject: Re: Excessive \"checking for updates\" on Travis CI\nTo: Mark Raynsford <org.apache.maven.user@io7m.com>\nCc: Maven Users List <users@maven.apache.org>\nReferences: <20170629171323.1c14b187@copperhead.int.arc7.info>\n <bcf4a4e8-0692-e305-a426-ad0fe1c03348@gmx.de>\n <20170629175018.1ab0f4cb@copperhead.int.arc7.info>\nFrom: Karl Heinz Marbaise <khmarbaise@gmx.de>\nMessage-ID: <2d9b1bd2-3bc8-cae1-340b-93105be498f2@gmx.de>\nDate: Sun, 2 Jul 2017 11:22:03 +0200\nUser-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:52.0)\n Gecko/20100101 Thunderbird/52.2.1\nMIME-Version: 1.0\nIn-Reply-To: <20170629175018.1ab0f4cb@copperhead.int.arc7.info>\nContent-Type: text/plain; charset=utf-8; format=flowed\nContent-Language: en-GB\nContent-Transfer-Encoding: 7bit\nX-Provags-ID: V03:K0:uDI98t4dZCkGCB0mHrf31WdSOfoTQlu7nzzTlCNOlUt7XR0FoD5\n vec27kSJNQUMbrcIL2Wa7rOUf0WLze0By+CJtxm1p8dwLZx9kL1Rcqw3AqmBSb6U/gMn5JI\n jSV7mtjgyYAdWtrpKd48FvCPPtoF/CjB7HzwuSRW27YpLhvd5GDwM7xHyhKO9ELOhj323Kw\n nGwLj6NpxL020VtDWa4Sw==\nX-UI-Out-Filterresults: notjunk:1;V01:K0:f116znUFMIc=:WqHkKBZQMpFeFHk+hfw0un\n wxe3viiD8EgzFojRojkwaTCVDab3uvPQi9pXUNqV0QEvaZAOvXJ+aVFQGQzH3norLc+ywF+o2\n ZdK3GCEOEgmwd4MME/8Fplehy7yhldg6G5gEjp2g1rBUH5PrKez+VQI1Dmc/PzRrMfUzx0GV3\n 9OGSGhoxOfXagjXhxVj5o37aKrmUmSaLKjNd0PwYHd2hIonAljxYUOMmgn1usT8PDw/I2xwKo\n wFahPl/LBk5fHo3GHhQDTwFR4CRg/4BQfuUw116IFvdtbf2aQA3ta9PMm/jWdOxjX12vVw56r\n 1x4xs2NbqahFeYAqGgQgY44LCps+6PQEAUTgvTtmDF3SGrmg7/C5Tqo7Uac3KQ9qqbgZnXLkz\n +aypb3CHz7mcSDAki6KQXL9bsBIcLIya2tZu9tVq1zVKnlZWlTHYAY+YqVkuU7tMlWkUv4FvE\n sAue5YnBGuQZqK4WUY5IOo0grDQ4QXskudPtr26bAbK3fTB0HPFo/Pf+SuH7aSK00ChZCjmHr\n o1RFYETIWi8AVi6UVp7h0zQ+ZG/C8q6wU4roWwbwMG6Fb3e/LqoCN3tovrdk0dE4KhTsscuuU\n GN7voQMJuwPAHS22WLUQCZL9FwwQQpCp3q2CatTkRcdE36MmPH0xQFbrCsQNBZjuLeZQaVP7Z\n 68IK7tAxL2aQMEbFFIMsG0PHOnn/OMOJW5MqD4RF90r62JCR5NT6O/dqS+z2coSdsXsZRIzcW\n /Dc9JOGTelGIxcTPXhxy63f10UpwzArJWVM57woZDZp+9/ULThB9VMtY0un4gsNluhfhKYlCA\n yOOsPtA\n\nHi Mark,\n\nOn 29/06/17 19:50, Mark Raynsford wrote:\n> On 2017-06-29T19:38:40 +0200\n> Karl Heinz Marbaise <khmarbaise@gmx.de> wrote:\n> \n>> HI,\n>>\n>> first you are using version ranges...that\'s the reason for that...\n>>\n>> Simple recommendation I can give is:  Don\'t use version ranges...\n>>\n>>\n> \n> Hello.\n> \n> I maintain ~70 projects with complex interdependencies (this graph\n> shows a subset of them):\n> \n>    https://raw.githubusercontent.com/io7m/universe/master/io7m.png\n> \n> If I don\'t use version ranges, then when I update one dependency, I get\n> to update a ton of other packages too. I make frequent releases. Without\n> version ranges, my day would quickly be consumed by\n> version-number-incrementing busy work across a large number of packages\n> instead of getting actual development work done.\n> \n> I make strong guarantees about API compatibility with my own packages,\n> including using japicmp to analyze the bytecode to ensure that I follow\n> semantic versioning correctly. Therefore, I use version ranges, and\n> have been for years.\n> \n> Is there something else I could be doing?\n\nMaybe you can do this by using versions-maven-plugin to update deps etc. \nvia CI solution so you don\'t use versions ranges and prevent the request \nagainst your repository manager and have the job done via the computer \ninstead of doing it manually and waisting your time with updating this...\n\n\nKind regards\nKarl Heinz Marbaise"""

  val input1: String =
    """background-color: #A03300;
    |background-image: url(img/header100.png);
    |background-position: top center;
    |background-repeat: repeat-x;
    |background-size: 2160px 108px;
    |margin: 0;
    |height: 108px;  
    |$$$:*;  
    |width: 100%;""".stripMargin

  for (patternMatch <- keyValMails.findAllMatchIn(input)) {

    val key = patternMatch.group(1);

    val value = patternMatch.group(2);

    println(s"key: ${key} value: ${value}")

  }

}