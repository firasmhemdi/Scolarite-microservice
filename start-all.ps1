$ErrorActionPreference = "Stop"

$env:JAVA_HOME = "C:\Program Files\Java\jdk-17.0.18"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"

$root = Split-Path -Parent $MyInvocation.MyCommand.Path
$pidFile = Join-Path $root ".running-services.pids"
$services = @(
    @{ Name = "config-service"; Wait = 10 },
    @{ Name = "eureka-service"; Wait = 18 },
    @{ Name = "etudiant-service"; Wait = 5 },
    @{ Name = "enseignant-service"; Wait = 5 },
    @{ Name = "stage-service"; Wait = 8 },
    @{ Name = "scolarite-service"; Wait = 8 },
    @{ Name = "admin-service"; Wait = 5 },
    @{ Name = "gateway-service"; Wait = 5 }
)

if (Test-Path $pidFile) {
    Write-Host "Des services semblent deja demarres. Lancez d'abord stop-all.ps1 si necessaire."
    exit 1
}

$processIds = @()

foreach ($service in $services) {
    $name = $service.Name
    $jar = Join-Path $root "$name\target\$name-0.0.1-SNAPSHOT.jar"
    $workDir = Join-Path $root $name

    Write-Host "Demarrage de $name..."
    $process = Start-Process -FilePath "$env:JAVA_HOME\bin\java.exe" `
        -ArgumentList "-jar", $jar `
        -WorkingDirectory $workDir `
        -WindowStyle Hidden `
        -PassThru
    $processIds += $process.Id
    Start-Sleep -Seconds $service.Wait
}

$processIds | Set-Content $pidFile
Write-Host ""
Write-Host "Tous les services sont demarres."
Write-Host "Interface : http://localhost:8081/"
Write-Host "Eureka    : http://localhost:8761/"
