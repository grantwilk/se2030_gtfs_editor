Class Structure Changes:
* Rephrased “Active Element” terminology to “Selected Element” in GUI diagram
* Replaced dependency arrows with association arrows in File and GUI diagrams
* Made GTFSID’s list of existing IDs use GTFSID objects instead of Strings
* Replaced GTFSElement subclass constructor’s ID parameters with Strings
* Replaced Coordinate class with Point2D class in Trip and Stop
* Removed windowTitle attribute from GTFSDialogController
* Removed null constructors from GTFSID subclasses
* Udpated GTFSID.exists(String) to accept a GTFSID for the parameter instead
