# J-ArcDps-BHUD-Adapter
 This is a very simple library that makes it easier to receive and process events sent from [arcdps-bhud](https://github.com/blish-hud/arcdps-bhud) in Java.

## Requirements
Java 11+
implemented and tested against arcdps-bhud v0.3.1

## Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.Bowtie8904</groupId>
        <artifactId>J-ArcDps-BHUD-Adapter</artifactId>
        <version>1.1</version>
    </dependency>
</dependencies>
```

## Simple example
Listener
```java
public class ExampleListener implements BHudEventListener
{
    @Override
    public byte[] onImguiEvent(ImguiEvent event)
    {
        System.out.println(event);
        return null; // returned byte array will be sent to B-Hud, return null to not send anything
    }

    @Override
    public byte[] onLocalCombatEvent(CombatEvent event)
    {
        System.out.println(event);
        return null; // returned byte array will be sent to B-Hud, return null to not send anything
    }

    @Override
    public byte[] onAreaCombatEvent(CombatEvent event)
    {
        System.out.println(event);
        return null; // returned byte array will be sent to B-Hud, return null to not send anything
    }
}
```

Main setup of client
```java
public class Example
{
    public static void main(String[] args)
    {
        // create a new client and tell it where the Guild Wars 2 application is
        var client = new BHudEventClient("C:\\Program Files\\Guild Wars 2\\Gw2-64.exe");

        // set the listener that should receive events
        client.setEventListener(new ExampleListener());

        // start listening
        client.start();
    }
}
```
