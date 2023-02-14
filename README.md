# About
DynVarMap is zero dependency easy and straight forward way to manage attribute-like runtime variables for Java.

# How To
#### Creating a new instance
To create a new instance all you need to do is create a new VarMap object.
```java
VarMap map = new VarMap();
```

#### Serializing/saving & loading from disk
You can serialize the map using the built-in serializer, it uses it's own format similar to .ini.
Deserializing supports .ini format as well if you perfer to have hand-written configurations.
```java
new VarMapSerializer("map.ini", map, true).save();
new VarMapSerializer("map.ini", map, true).load();
```

#### Creating new variables
To create a new variable all you need to do is define the type first, then enter the unique variable name you would like to label it.
```java
boolean existsAlready = map.getBoolean("githubBoolExample").get();
```
To supply a default value you can do it on type constructor
```java
boolean existsAlready = map.getBoolean("githubBoolExample", false).get();
```

#### Method chainning
To fully leverage DynVar the trick is to chain methods to create easy-to-read one liners.
```java
//create a new variable if it doesn't exist yet and check if it's been set to true, if it has execute the code below
if(map.getBoolean("githubBoolExample").get())
```
```java
//create a new variable if it doesn't exist yet, check if it's been set as true, if it hasn't execute the code below, either way set it to being set as true
if(!map.getBoolean("githubBoolExample").getAndSet(true))
```
```java
//create a new variable if it doesn't exist yet, add one, check if it equals any of the numbers, if it does execute the code below
if(map.getInt("githubIntExample").add(1).equalsAny(13, 21, 34, 55, 89))
```

#### Time & Custom Types
DynVar supports custom types, one built-in example common is system time interaction. The Time type is based on the Long type, this means it comes with all of the helper functions such as add, subtract, divide and multiply.
```java
map.getTime("githubTimeExample").setNow(); //set the stop-watch to start counting now
if(map.getTime("githubTimeExample").hasPassed(30_000)) //return true if 30 seconds have passed
```
