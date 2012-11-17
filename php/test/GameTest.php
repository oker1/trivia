<?php
/**
 * @author oker <takacs.zsolt@ustream.tv>
 */

include __DIR__ . '/../Game.php';

/**
 * GameTest
 *
 * @author oker <takacs.zsolt@ustream.tv>
 */
class GameTest extends PHPUnit_Framework_TestCase
{
    public function testWrongAnswer_GetsIntoPenalty() {
        $test = function() {
            $aGame = new Game();

            $aGame->add("Chet");
            $aGame->add("David");

            $aGame->roll(1);
            $aGame->wrongAnswer();
        };

        $expectedOutput = <<<END
Chet was added
They are player number 1
David was added
They are player number 2
Chet is the current player
They have rolled a 1
Chet's new location is 1
The category is Science
Science Question 0
Question was incorrectly answered
Chet was sent to the penalty box

END;

        $this->assertOutput($test, $expectedOutput);
    }

    public function testCorrectAnswer_GetsCoin() {
        $test = function() {
            $aGame = new Game();

            $aGame->add("Chet");
            $aGame->add("David");

            $aGame->roll(1);
            $aGame->wasCorrectlyAnswered();
        };

        $expectedOutput = <<<END
Chet was added
They are player number 1
David was added
They are player number 2
Chet is the current player
They have rolled a 1
Chet's new location is 1
The category is Science
Science Question 0
Answer was corrent!!!!
Chet now has 1 Gold Coins.

END;

        $this->assertOutput($test, $expectedOutput);
    }

    public function testCorrectAnswerGettingOutOfPenaltyBox_GetsCoin(){
        $test = function(){
            $aGame = new Game();

            $aGame->add("Chet");
            $aGame->add("David");

            $aGame->roll(1);
            $aGame->wrongAnswer();
            $aGame->roll(1);
            $aGame->wrongAnswer();
            $aGame->roll(3);
            $aGame->wasCorrectlyAnswered();
        };

        $expectedOutput = <<<END
Chet was added
They are player number 1
David was added
They are player number 2
Chet is the current player
They have rolled a 1
Chet's new location is 1
The category is Science
Science Question 0
Question was incorrectly answered
Chet was sent to the penalty box
David is the current player
They have rolled a 1
David's new location is 1
The category is Science
Science Question 1
Question was incorrectly answered
David was sent to the penalty box
Chet is the current player
They have rolled a 3
Chet is getting out of the penalty box
Chet's new location is 4
The category is Pop
Pop Question 0
Answer was correct!!!!
Chet now has 1 Gold Coins.

END;

        $this->assertOutput($test, $expectedOutput);
    }

    public function testRollingEvenInPenaltyBox_DoesNotGetOut(){
        $test = function() {
            $aGame = new Game();

            $aGame->add("Chet");
            $aGame->add("David");

            $aGame->roll(1);
            $aGame->wrongAnswer();
            $aGame->roll(1);
            $aGame->wrongAnswer();
            $aGame->roll(2);
            $aGame->wasCorrectlyAnswered();
        };

        $expectedOutput = <<<END
Chet was added
They are player number 1
David was added
They are player number 2
Chet is the current player
They have rolled a 1
Chet's new location is 1
The category is Science
Science Question 0
Question was incorrectly answered
Chet was sent to the penalty box
David is the current player
They have rolled a 1
David's new location is 1
The category is Science
Science Question 1
Question was incorrectly answered
David was sent to the penalty box
Chet is the current player
They have rolled a 2
Chet is not getting out of the penalty box

END;

        $this->assertOutput($test, $expectedOutput);
    }

    private function assertOutput($callable, $expectedOutput) {
        ob_start();

        call_user_func($callable);

        $output = ob_get_clean();

        $this->assertEquals($expectedOutput, $output);
    }
}

?>