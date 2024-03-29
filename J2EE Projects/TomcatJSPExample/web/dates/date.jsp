<html>
<!--
  Copyright 2004 The Apache Software Foundation

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->

<%@ page session="false"%>

<body bgcolor="white">
<jsp:useBean id='clock' scope='page' class='dates.JspCalendar' type="dates.JspCalendar" />

<font size=4>
<ul>
<li>	Day of month: is  <jsp:getProperty name="clock" property="dayOfMonth"/>
<li>	Years from 1992: is  <jsp:getProperty name="clock" property="yearsFrom1992"/>
<li>	Month: is  <jsp:getProperty name="clock" property="month"/>
<li>	Time: is  <jsp:getProperty name="clock" property="time"/>
<li>	Date: is  <jsp:getProperty name="clock" property="date"/>
<li>	Day: is  <jsp:getProperty name="clock" property="day"/>
<li>	Day Of Year: is  <jsp:getProperty name="clock" property="dayOfYear"/>
<li>	Week Of Year: is  <jsp:getProperty name="clock" property="weekOfYear"/>
<li>	era: is  <jsp:getProperty name="clock" property="era"/>
<li>	DST Offset: is  <jsp:getProperty name="clock" property="DSTOffset"/>
<li>	Zone Offset: is  <jsp:getProperty name="clock" property="zoneOffset"/>
</ul>
</font>

</body>
</html>
