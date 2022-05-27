#!/bin/bash

USER="pi"
HOST="192.168.10.14"

scp ../chores-backend/target/chore-manager.jar $USER@$HOST:/tmp

ssh $USER@$HOST 'sudo systemctl stop chores-manager'
ssh $USER@$HOST 'mv /tmp/chore-manager.jar /opt/chore-manager'
ssh $USER@$HOST 'sudo systemctl start chores-manager'
