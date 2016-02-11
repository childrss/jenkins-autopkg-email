// This script should be in Jenkins/Home/email-templates
GENERAL INFO
BUILD ${build.result}
Build URL: ${rooturl}${build.url}
Project: ${project.name}
Date of build: ${it.timestampString}
Build duration: ${build.durationString}
<%
import java.util.regex.*
def log = build.getLog(1000)
def NewItemsImportedSearch = log =~ /new items were imported/
def NewItemsDownloaded = log =~ /new items were downloaded/
def NothingDone = log =~ /Nothing downloaded, packaged or imported/

if (NothingDone) {
        // Build is successful, but nothing imported... so no e-mail
        cancel=true;
        // M@ 17Oct2014: THIS IS BEING HANDLED BY A PRE-SEND SCRIPT
        // as it seemed to do no good here :-(

%>

Nothing downloaded, packaged or imported... SO WHY ARE WE SEEING THIS E-MAIL?!?@#??%??!

<%
} else if (NewItemsImportedSearch) {
%>

NEW ITEM AVAILABLE IN MUNKI
A new version of ${project.name} was downloaded (autopkg: /Users/Shared/AutoPkg/Cache/${project.name} and imported into the munki repository (/Users/Shar                  ed/munki_repo/pkgs/).

<%
} else if (NewItemsDownloaded) {
%>

NEW ITEM DOWNLOADED TO AutoPkg Cache
A new version of ${project.name} was downloaded to the AutoPkg Cache -- (autopkg: /Users/Shared/AutoPkg/Cache/${project.name} BUT NOT imported into the m                  unki repository.

<%
} else {
%>

FAILURE?
<%
}
%>

CONSOLE OUTPUT
<% log.each()
{ line -> %>
${line}
<% }
%>
