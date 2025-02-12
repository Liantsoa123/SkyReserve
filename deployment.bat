    set bin-dir="bin"
    set web-dir="web"
    set lib-dir="lib"
    set conf-dir="config"

    set target-name="SkyReserve"
    set target-dir="C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps"

    rmdir /q/s "temp"

    mkdir "temp"


    echo R |  xcopy /s/e/y %web-dir% "temp/"
    echo R |  xcopy /s/e/y %lib-dir% "temp/WEB-INF/lib"
    echo R |  xcopy /s/e/y %bin-dir% "temp/WEB-INF/classes"
    echo R |  xcopy /s/e/y %conf-dir% "temp/WEB-INF"

@REM javac -d "temp/WEB-INF/classes" %src-dir%/*.java
jar -cvf %target-name%.war -C temp .

echo D | xcopy /q/y %target-name%.war %target-dir%  

del %target-name%.war
rmdir /q/s "temp" 
