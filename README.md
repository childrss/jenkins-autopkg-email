# jenkins-autopkg-email
customization scripts for Jenkins that sends e-mail notifications when AutoPkg imports a new package or is failing

# Requires:
    Extended E-mail Notification (Email-ext) plugin -- https://wiki.jenkins-ci.org/display/JENKINS/Email-ext+plugin
    Inject Environmental Variables (EnvInject) plugin -- https://wiki.jenkins-ci.org/display/JENKINS/EnvInject+Plugin
    
#Default Subject
$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!

#Default Content
${SCRIPT, template="autopkg-munki-new-items.groovy"}

#Default Pre-send Script

-- see file

# IS_TROUBLEMAKER

AutoPkg uses HTTP headers to tell if a file on a software developer's website has changed.  If the website developer uses mirrors, or a content delivery network to deliver their software the HTTP headers may change from mirror to mirror (or not be sent at all) causing AutoPkg to download the software again (triggering an e-mail to be sent).  See AutoPkg FAQ item #1, https://github.com/autopkg/autopkg/wiki/FAQ

Using the Inject Environment Variables plug-in you can tell the pre-send script of the Extended E-mail Notification (email-ext) plugin to ignore these downloads for known-troublemakers (so far in our experience, VLC, malwarebytes, MacTex, XLD)

Add 'Inject Environment Variables' in the project configuration build steps and set the Properties Content text field to 

             IS_TROUBLEMAKER=true

From there the default pre-send script takes over and determines whether to send the e-mail if there's only a download but no import into munki (which may indicate an error and should be looked at by a human to determine if it's a troublemaker or not).

Lines in the Console Output beginning with " **" are diagnostic println's from the pre-send script, and the line that begins with '==' tells you which branch of the if statement is setting whether to cancel sending the e-mail and why.

Comments/improvement on the code clarity is welcomed!
