<?php

include_once __DIR__.'/Game.php';

$notAWinner;

  $aGame = new Game();
  
  $aGame->add("Chet");
  $aGame->add("Pat");
  $aGame->add("Sue");
  
  
  $aGame->run();
  
