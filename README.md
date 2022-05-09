# CMPE 202 Individual project

<h3>Class Diagram</h3>

![Architecture Diagram](./diagram/Class%20Diagram1.png)


<h3>Design Patterns used</h3>

#### Singleton Pattern
```
Since the access to the inventory database needs to be in a synchornized manner to 
avoid inconsistencies. Singleton design pattern was used to acheive the same. 
The participating class is InventoryTable 
```


#### Composite Pattern
```
Any order can can be orgainsed into a whole part hierarchy depending on the various
subcategories. To capture that composite design pattern was used. The participating 
classes were Order(abstract class), OrderAggregate for internal nodes & Order Leaf 
for leaf nodes. 
```