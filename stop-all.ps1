$root = Split-Path -Parent $MyInvocation.MyCommand.Path
$pidFile = Join-Path $root ".running-services.pids"

if (Test-Path $pidFile) {
    Get-Content $pidFile | ForEach-Object {
        $pidNumber = [int]$_
        Stop-Process -Id $pidNumber -Force -ErrorAction SilentlyContinue
    }

    Remove-Item $pidFile -Force
}

$escapedRoot = [regex]::Escape($root)
$processes = Get-CimInstance Win32_Process |
    Where-Object { $_.CommandLine -match $escapedRoot }

foreach ($process in $processes) {
    Stop-Process -Id $process.ProcessId -Force -ErrorAction SilentlyContinue
}

Write-Host "Services arretes."
