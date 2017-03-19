Command Design Pattern
----------------------
A behavioral design pattern in which an oject is used to
represent and encapsulate all of the information needed to
call a method at a later time


* Allows you to store lists of code that is exec'd at a later time, or many times.
* Client wants a specific Command to run when `execute()` is called
* Invoker transfers the Command to another object called a Receiver to exec the correct code

* Allows you to set aside a list of commands for later use
* A class is a great place to store procedures you want to be exec'd
* You can store multiple commands
* You can implement undo features
