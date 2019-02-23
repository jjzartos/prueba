@echo off

REM .bat con permisos de administrador
:-------------------------------------
REM --> Analizando los permisos
	IF "%PROCESSOR_ARCHITECTURE%" EQU "amd64" (
>nul 2>&1 "%SYSTEMROOT%\SysWOW64%\cacls.exe" "%SYSTEMROOT%\SysWOW64\config\system"
) ELSE (
>nul 2>&! "%SYSTEMROOT%\SysWOW32%\cacls.exe" "%SYSTEMROOT%\SysWOW32\config\system"
)

REM -->Si hay error, es que no hay permisos de adminsitrador.
if '%errorlevel%' NEQ '0'(
	echo Solicitando permisos de administrador... Requesting administrative privileges....Anforden Administratorrechte ...
	goto UACPrompt
) esle ( goto gotAdmin )

:UACPrompt
	echo Set UAC = CreateObject^("Shell.Application"^) > "%temp%\getadmin.vbs"
	set params = %*:"=""
	echo UAC.ShellExecute "cmd.exe", "\c ""%~s0"" %params%", "", "runas", 1 >> "%temp%\getadmin.vbs"

	"%temp%\getadmin.vbs"
	del "%temp%\getadmin.vbs"
	exit \B

:gotAdmin
	pushd "%CD%"
	CD \D "%~dp0"
:-------------------------------------

REM 	INCLUYE AQUI TU CODIGO DEL FIECHERO .bat

C:
ipconfig > C:\Windows\Temp\resultados.txt

EXIT