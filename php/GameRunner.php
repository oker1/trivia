<?php

include_once __DIR__.'/Game.php';

  $aGame = new Game(new Input());
  
  $aGame->add("Chet");
  $aGame->add("Pat");
  $aGame->add("Sue");
  
  
  $aGame->run();
  
