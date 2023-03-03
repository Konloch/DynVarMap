# DynVarMap
DynVarMap is zero dependency easy and straight forward way to manage attribute-like runtime variables for Java.

## How To Add As Library
Add it as a maven dependency or just [download the latest release](https://github.com/Konloch/DynVarMap/releases).
```xml
<dependency>
  <groupId>com.konloch</groupId>
  <artifactId>DynVarMap</artifactId>
  <version>1.0.1</version>
</dependency>
```

## Links
* [Website](https://konloch.com/DynVarMap/)
* [Discord Server](https://discord.gg/aexsYpfMEf)
* [Download Releases](https://konloch.com/DynVarMap/releases)

## How To Use
#### Creating a new instance
To create a new instance all you need to do is create a new VarMap object.
```java
VarMap map = new VarMap();
```

#### Creating & accessing new variables
To create a new variable all you need to do is define the type first, then enter the unique variable name you would like to use.
```java
boolean booleanValue = map.getBoolean("githubBoolExample");
```
To supply a default value you can do it while grabbing the value.
```java
boolean booleanValue = map.getBoolean("githubBoolExample", false);
```
To access the DynVarMap field, you need to call upon the `getVar*` function. This exposes the API to allow method chaining on the variable.
```java
boolean booleanValue = map.getVarBoolean("githubBoolExample", false).get();
```

#### Method chainning
To fully leverage DynVarMap the trick is to chain methods to create easy-to-read one liners.
```java
//create a new variable if it doesn't exist yet and check if it's been set to true, if it has execute the code below
if(map.getVarBoolean("githubBoolExample").get())
```
```java
//create a new variable if it doesn't exist yet, check if it's been set as true, if it hasn't execute the code below, either way set it to being set as true
if(!map.getVarBoolean("githubBoolExample").getThenSet(true))
```
```java
//create a new variable if it doesn't exist yet, add one, check if it equals any of the numbers, if it does execute the code below
if(map.getVarInt("githubIntExample").add(1).equals(13, 21, 34, 55, 89))
```

#### Time & Custom Types
DynVarMap supports custom types, one built-in example common is system time interaction. The Time type is based on the Long type, this means it comes with all of the helper functions such as add, subtract, divide and multiply.
```java
map.getTime("githubTimeExample").setNow(); //set the stop-watch to start counting now
if(map.getVarTime("githubTimeExample").hasPassed(30_000)) //return true if 30 seconds have passed
```

#### Serializing/saving & loading from disk
You can serialize the map using the built-in serializer, it uses it's own format similar to .ini.
Deserializing supports .ini format as well if you perfer to have hand-written configurations.
```java
new VarMapSerializer("map.ini", map, true).save();
new VarMapSerializer("map.ini", map, true).load();
```
