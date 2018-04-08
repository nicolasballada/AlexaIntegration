$FileName = "AlexaControl.txt"
$FileTime = Get-Date

# endless loop
for () {
    $file = Get-Item $FileName
    if ($FileTime -ne $file.LastWriteTime) {
        Get-Process powerpnt* | Stop-Process
        Invoke-Item test.bat
    }
    $FileTime = $file.LastWriteTime
    Start-Sleep 5
}