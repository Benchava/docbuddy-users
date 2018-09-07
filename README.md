# DocBuddy
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/693624cacd6845eeb758fc5fe19c14cc)](https://app.codacy.com/app/nbantar/DocBuddy?utm_source=github.com&utm_medium=referral&utm_content=nbantar/DocBuddy&utm_campaign=Badge_Grade_Dashboard)
[![Build Status](https://travis-ci.org/nbantar/DocBuddy.svg?branch=master)](https://travis-ci.org/nbantar/DocBuddy)
[![CodeCovWidget](https://codecov.io/gh/kubernetes/minikube)](https://codecov.io/gh/nbantar/DocBuddy/branch/master/graph/badge.svg)

Note: This is a continuous WORK IN PROGRESS.

SUMMARY

DocBuddy is a REST API developed as part of a test for implementing a small micro-services architecture.
The complete set of micro-services are meant to work as a small system to manage Doctor-Client relationships and also to
serve as a way of having a centralized clinical history for them.


TECHNOLOGIES

DocBuddy is built using Java 8, Gradle, Spring-Boot, Lombok and Firebase among other technologies.


HOW TO RUN IT

DocBuddy uses Spring-Boot so, once the project is cloned and built (using Gradle), all you have to do is run the 
"bootRun" gradle task and the service will come up and be accessible in http://localhost:8080.
The API can be hit using Postman or any other tool that you see fit.
All the endpoints consume and produce JSON payloads.