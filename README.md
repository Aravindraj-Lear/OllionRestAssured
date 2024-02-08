# OllionRestAssured
This repository contains Rest Assured automation scripts for StackOverflow website Badge API (ids, recipients, tags).

# Overview
The test automation scripts are written in Java using the Rest Assured library. Response schema validation, Status code check and some basic assert were covered.

Note: If we try running too many times, we will start getting Status-502 error with message "{ "error_id": 502, "error_message": "too many requests from this IP, more requests available in 19564 seconds", "error_name": "throttle_violation" }" and all test will fail.

# Prerequisites
Before running the automation scripts, ensure you have the following installed:

- Java Development Kit (JDK) - 21
- Maven
- TestNG (for running the test suite)

# Execution
Use Testng.xml file in project for execution

# Report
Reports will be available in "test-output" folder >> "emailable-report.html"
