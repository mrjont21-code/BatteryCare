#!/bin/sh

#
# Copyright © 2015-2021 the original authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Attempt to set APP_HOME
# Resolve links: $0 may be a symlink
app_path=$0

# Need this for daisy-chained symlinks.
while
    APP_HOME=${app_path%"${app_path##*/}"}
    [ -n "$APP_HOME" ] &&
    APP_HOME=$(cd "$APP_HOME" 2>/dev/null && pwd) || break
    app_path=$(readlink "$app_path") || break
done

APP_HOME=$(cd "${APP_HOME:-./}" && pwd -P) || exit

APP_NAME="Gradle"
APP_BASE_NAME=${0##*/}

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS='" -Xmx64m" "-Xms64m"'

# Use the maximum available, or set MAX_FD != maximum.
MAX_FD=maximum

warn() {
    echo "$*" >&2
}

die() {
    echo "$*" >&2
    exit 1
}

case $(uname) in
CYGWIN* | MINGW* | MSYS*)
    cygwin=true
    ;;
*)
    cygwin=false
    ;;
esac

if $cygwin; then
    APP_HOME=$(cygpath --path --mixed "$APP_HOME")
    APP_HOME=$(cygpath --path --absolute "$APP_HOME")
fi

if ! command -v java >/dev/null 2>&1; then
    die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH."
fi

if [ ! -x "$APP_HOME/gradlew" ]; then
    chmod +x "$APP_HOME/gradlew"
fi

exec "$APP_HOME/gradlew" "$@"
