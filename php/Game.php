<?php
function echoln($string) {
  echo $string."\n";
}

class Game {
    var $players;
    var $places;
    var $purses ;
    var $inPenaltyBox ;

    private $questionsByCategory;
    private $categoryMapping;

    var $currentPlayer = 0;
    var $isGettingOutOfPenaltyBox;

    function  __construct(){

   	    $this->players = array();
        $this->places = array(0);
        $this->purses  = array(0);
        $this->inPenaltyBox  = array(0);

        $this->initializeCategoryMapping();

        $this->initializeQuestions();
    }

    private function initializeQuestions()
    {
        $categories = array_map('trim', file(__DIR__ . '/config/categories.txt'));

        foreach ($categories as $category) {
            $this->questionsByCategory[$category] = array();
            for ($i = 0; $i < 50; $i++) {
                array_push($this->questionsByCategory[$category], "{$category} Question {$i}");
            }
        }
    }

    private function initializeCategoryMapping()
    {
        $this->categoryMapping = array_map('trim', file(__DIR__ . '/config/places.txt'));
    }

	function add($playerName) {
	   array_push($this->players, $playerName);
	   $this->places[$this->howManyPlayers()] = 0;
	   $this->purses[$this->howManyPlayers()] = 0;
	   $this->inPenaltyBox[$this->howManyPlayers()] = false;

	    echoln($playerName . " was added");
	    echoln("They are player number " . count($this->players));
		return true;
	}

	function howManyPlayers() {
		return count($this->players);
	}

	function  roll($roll) {
		echoln($this->players[$this->currentPlayer] . " is the current player");
		echoln("They have rolled a " . $roll);

		if ($this->inPenaltyBox[$this->currentPlayer]) {
			if ($roll % 2 != 0) {
				$this->isGettingOutOfPenaltyBox = true;

				echoln($this->players[$this->currentPlayer] . " is getting out of the penalty box");
			$this->places[$this->currentPlayer] = $this->places[$this->currentPlayer] + $roll;
				if ($this->places[$this->currentPlayer] > 11) $this->places[$this->currentPlayer] = $this->places[$this->currentPlayer] - 12;

				echoln($this->players[$this->currentPlayer]
						. "'s new location is "
						.$this->places[$this->currentPlayer]);
				echoln("The category is " . $this->currentCategory());
				$this->askQuestion();
			} else {
				echoln($this->players[$this->currentPlayer] . " is not getting out of the penalty box");
				$this->isGettingOutOfPenaltyBox = false;
				}

		} else {

		$this->places[$this->currentPlayer] = $this->places[$this->currentPlayer] + $roll;
			if ($this->places[$this->currentPlayer] > 11) $this->places[$this->currentPlayer] = $this->places[$this->currentPlayer] - 12;

			echoln($this->players[$this->currentPlayer]
					. "'s new location is "
					.$this->places[$this->currentPlayer]);
			echoln("The category is " . $this->currentCategory());
			$this->askQuestion();
		}

	}

	function  askQuestion() {
        echoln(array_shift($this->questionsByCategory[$this->currentCategory()]));
	}


    function currentCategory() {
        return $this->categoryMapping[$this->places[$this->currentPlayer]];
	}

	function wasCorrectlyAnswered() {
		if ($this->inPenaltyBox[$this->currentPlayer]){
			if ($this->isGettingOutOfPenaltyBox) {
				echoln("Answer was correct!!!!");
			$this->purses[$this->currentPlayer]++;
				echoln($this->players[$this->currentPlayer]
						. " now has "
						.$this->purses[$this->currentPlayer]
						. " Gold Coins.");

				$winner = $this->didPlayerWin();
				$this->currentPlayer++;
				if ($this->currentPlayer == count($this->players)) $this->currentPlayer = 0;

				return $winner;
			} else {
				$this->currentPlayer++;
				if ($this->currentPlayer == count($this->players)) $this->currentPlayer = 0;
				return true;
			}



		} else {

			echoln("Answer was corrent!!!!");
		$this->purses[$this->currentPlayer]++;
			echoln($this->players[$this->currentPlayer]
					. " now has "
					.$this->purses[$this->currentPlayer]
					. " Gold Coins.");

			$winner = $this->didPlayerWin();
			$this->currentPlayer++;
			if ($this->currentPlayer == count($this->players)) $this->currentPlayer = 0;

			return $winner;
		}
	}

	function wrongAnswer(){
		echoln("Question was incorrectly answered");
		echoln($this->players[$this->currentPlayer] . " was sent to the penalty box");
	$this->inPenaltyBox[$this->currentPlayer] = true;

		$this->currentPlayer++;
		if ($this->currentPlayer == count($this->players)) $this->currentPlayer = 0;
		return true;
	}


	function didPlayerWin() {
		return !($this->purses[$this->currentPlayer] == 6);
	}
}
