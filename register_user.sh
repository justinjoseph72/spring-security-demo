#!/bin/bash
set -o pipefail
IFS=$'\n\t'

input_email=$1

validate_input(){
if [ -z "$input_email" ]
then
echo 'no input'
exit 1
else
echo ${input_email}
fi
}

register_user(){
curl -X GET http://localhost:8089/check/register -H 'fe_token: 9989'
}

validate_input
echo 'registering user'
register_user