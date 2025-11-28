$ErrorActionPreference = "Stop"

$root = Convert-Path "."
$dest = Join-Path $root "SRC_STRUCTURE_AND_CODE.md"
$structureLines = Get-ChildItem -Path src -Recurse | Sort-Object FullName | ForEach-Object {
    $rel = $_.FullName.Substring($root.Length + 1)
    if ($_.PSIsContainer) { "$rel\" } else { $rel }
}

$nl = "`n"

$headerLines = @("# CW2025 src overview", "", "## File structure", "", '```text') + $structureLines + @('```')

Set-Content -Path $dest -Value ($headerLines -join $nl) -Encoding UTF8

$binaryExt = @(".png", ".ttf")
$binaryFiles = Get-ChildItem src -Recurse -File | Where-Object { $binaryExt -contains $_.Extension.ToLower() }

if ($binaryFiles.Count -gt 0) {
    $lines = $binaryFiles | ForEach-Object { "- " + ($_.FullName.Substring($root.Length + 1)) }
    $binarySection = @("", "Binary assets (not embedded):", "") + $lines

    Add-Content -Path $dest -Value ($binarySection -join $nl) -Encoding UTF8
}

Add-Content -Path $dest -Value "${nl}## Source code$nl" -Encoding UTF8

$files = Get-ChildItem src -Recurse -File | Where-Object { -not ($binaryExt -contains $_.Extension.ToLower()) }

foreach ($file in $files) {
    $rel = $file.FullName.Substring($root.Length + 1)
    $lang = switch ($file.Extension.ToLower()) {
        ".java" { "java" }
        ".css" { "css" }
        ".fxml" { "xml" }
        ".md" { "markdown" }
        ".txt" { "text" }
        default { "text" }
    }
    $content = Get-Content -Path $file.FullName -Raw
    $sectionLines = @(
        "",
        "### $rel",
        ('```{0}' -f $lang),
        $content,
        '```'
    )
    Add-Content -Path $dest -Value ($sectionLines -join $nl) -Encoding UTF8
}
