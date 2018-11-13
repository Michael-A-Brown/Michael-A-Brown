REM sourceCode cred@MichaelABrown THIS SCRIPT IS OPEN SOURCE

REM This script disables a large amount of unneeded Services as well as implementing secure practices for Windows Vista and 7

REM use this script to enable basic security settings then analze system and network information with your the program of your choice
REM all system and network information is in seperate text files so you can load anaylze and perform event driven programming base on it


REM IMPORTANT READ: RUN in Admin mode for setting to change textfiles will not output when setting are being changed, then run script again without admin mode REM for diagnostic info
 
REM Disables guest account
net user guest /active:no
REM Turns on fire wall
netsh advfirewall set allprofiles state on
REM Runs Network Information commands and saves them to text file in current directory

REM Network Info 
ipconfig /all > ipConfig.txt

nbtstat -s > nbtstat.txt

route Print > curRoute.txt

arp -a  > arp_a.txt

getmac  > getMac.txt

REM firewall info
netsh advfirewall firewall show rule name=all dir=in type=dynamic > inboundFireRule.txt
netsh advfirewall show allprofiles > allProfiles.txt
REM User account info
systeminfo > curSymInfo.txt

net accounts  > userAccount.txt

net user > users.txt


REM Disable common unwanted services 

sc config "SharedAccess" start= disabled
sc stop "SharedAccess"
sc config "UmRdpService" start= disabled
sc stop "UmRdpService"
sc config "RemoteRegistry " start= disabled
sc stop "RemoteRegistry"
sc config "SessionEnv" start= disabled
sc stop "SessionEnv"
sc config "SSDPSRV" start= disabled
sc stop "SSDPSRV"
sc config "upnphost" start= disabled
sc stop "upnphost"
sc config "TermService" start= disabled
sc stop "TermService"
sc config "W3SVC" start= disabled
sc stop "W3SVC"
sc config "TrkWks" start= disabled
sc stop "TrkWks"
sc config "telnet" start= disabled
sc stop "telnet"
sc config "HomeGroupListener" start= disabled
sc stop "HomeGroupListener"
sc config "HomeGroupProvider" start= disabled
sc stop "HomeGroupProvider"
sc config "SharedAccess" start= disabled
sc stop "SharedAccess"
sc config "TapiSrv" start= disabled
sc stop "TapiSrv"
sc config "iphlpsvc" start= disabled
sc stop "iphlpsvc"
sc config "lltdsvc" start= disabled
sc stop "lltdsvc"
sc config "Netlogon" start= disabled
sc stop "Netlogon"
sc config "WPCSvc" start= disabled
sc stop "WPCSvc"
sc config "seclogon" start= disabled
sc stop "seclogon"
sc config "LanmanServer" start= disabled
sc stop "LanmanServer"
sc config "lmhosts" start= disabled
sc stop "lmhosts"
sc config "WebClient" start= disabled
sc stop "WebClient"
REM Disables unneeded features
Dism /online /Disable-Feature /FeatureName:TFTP 
Dism /online /Disable-Feature /FeatureName:TelnetClient 
Dism /online /Disable-Feature /FeatureName:RasRip 
Dism /online /Disable-Feature /FeatureName:IIS-WebServer  
Dism /online /Disable-Feature /FeatureName:MSMQ-Server 


REM Enables UAC
C:\Windows\System32\cmd.exe /k %windir%\System32\reg.exe ADD HKLM\SOFTWARE\Microsoft\Windows\CurrentVersion\Policies\System /v EnableLUA /t REG_DWORD /d 1 /f


REM test to see if excuting 
start "" cmd /c "echo Hello world!&echo(&pause"



