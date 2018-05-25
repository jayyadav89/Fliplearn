@echo off
echo parms are %1 %2
set projectLocation=%1
echo %projectLocation%
rem cd %projectLocation%
set environment=%2
echo %environment%
start ant -Denvironment=%environment%
pause