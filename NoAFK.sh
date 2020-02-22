echo "Bash version ${BASH_VERSION}..."
WINDOWID=$(xdotool search --class OSBuddy | head -1)

#for i in {0..10..2}
while true; 
  do 
     xdotool windowactivate --sync $WINDOWID key F$((1 + RANDOM % 12))
     sleep $((1 + RANDOM % 10))
 done