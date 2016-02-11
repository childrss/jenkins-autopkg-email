# jenkins-autopkg-email
customization scripts for Jenkins that sends e-mail notifications when AutoPkg imports a new package or is failing

This uses the Jenkins Extended E-mail Notification plugin -- https://wiki.jenkins-ci.org/display/JENKINS/Email-ext+plugin

#Default Subject
$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!

#Default Content
${SCRIPT, template="autopkg-munki-new-items.groovy"}

#Default Pre-send Script

-- see file
