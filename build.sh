echo "Startng build..."
./gradlew assembleDebug
echo "Buid finished! Moving artifacts to your home..."
mv app/build/outputs/apk/debug/*.apk ~/TheBigMayevsky.apk
echo "APK moved to ~/app-debug.apk"
echo "Running lint..."
./gragle lint
echo "Moving lint artifacts to ~/TheBigMayevsky.html"
mv app/build/reports/lint-results.html ~/TheBigMayevsky.html
echo "Ready. Happy debugging!"
