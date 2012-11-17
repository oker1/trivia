<?php
/**
 * @author oker <takacs.zsolt@ustream.tv>
 */

/**
 * GameRunnerTest
 *
 * @author oker <takacs.zsolt@ustream.tv>
 */
class GameRunnerTest extends PHPUnit_Framework_TestCase
{
    public function seedProvider() {
        return array_map(function ($filename) {
            return array(basename($filename), file_get_contents($filename));
        }, glob(__DIR__ . '/seeds/*'));
    }

    /**
     * @param $seed
     * @param $expectedOutput
     * @dataProvider seedProvider
     */
    public function testGameRunnerWithSeed($seed, $expectedOutput)
    {
        srand($seed);

        ob_start();
        include __DIR__ . '/../GameRunner.php';
        $output = ob_get_clean();
        $this->assertEquals($expectedOutput, $output);
    }

    public function recordOutput($seed) {
        srand($seed);

        ob_start();
        include __DIR__ . '/../GameRunner.php';
        $output = ob_get_clean();

        file_put_contents(__DIR__ . '/seeds/' . $seed, $output);
    }

    public function testCantGetOutOfPenaltyBox() {
        include __DIR__ . '/../Game.php';

        $inputMock = $this->getMockBuilder('Input')
            ->setMethods(array('answer', 'roll'))
            ->getMock();

        $this->mockRound($inputMock, 0, 1, false);
        $this->mockRound($inputMock, 1, 2, true);
        $this->mockRound($inputMock, 2, 3, true);
        $this->mockRound($inputMock, 3, 3, true);
        $this->mockRound($inputMock, 4, 5, true);

        ob_start();

        $aGame = new Game($inputMock);

        $aGame->add("Chet");
        $aGame->add("Pat");

        $aGame->round();
        $aGame->round();
        $aGame->round();
        $aGame->round();
        $aGame->round();

        $output = ob_get_clean();

        $expectedOutput = <<<END
Chet was added
They are player number 1
Pat was added
They are player number 2
Chet is the current player
They have rolled a 1
Chet's new location is 1
The category is Science
Science Question 0
Question was incorrectly answered
Chet was sent to the penalty box
Pat is the current player
They have rolled a 2
Pat's new location is 2
The category is Sports
Sports Question 0
Answer was corrent!!!!
Pat now has 1 Gold Coins.
Chet is the current player
They have rolled a 3
Chet is getting out of the penalty box
Chet's new location is 4
The category is Pop
Pop Question 0
Answer was correct!!!!
Chet now has 1 Gold Coins.
Pat is the current player
They have rolled a 3
Pat's new location is 5
The category is Science
Science Question 1
Answer was corrent!!!!
Pat now has 2 Gold Coins.
Chet is the current player
They have rolled a 5
Chet is getting out of the penalty box
Chet's new location is 9
The category is Science
Science Question 2
Answer was correct!!!!
Chet now has 2 Gold Coins.

END;

        $this->assertEquals($expectedOutput, $output);
    }

    private function mockRound($inputMock, $round, $roll, $answer)
    {
        $inputMock->expects($this->at($round * 2))
            ->method('roll')
            ->will($this->returnValue($roll));

        $inputMock->expects($this->at($round * 2 + 1))
            ->method('answer')
            ->will($this->returnValue($answer));
    }

    public function recording()
    {
        for ($i = 0 ; $i <= 1000; $i++) {
            $this->recordOutput($i);
        }
    }
}

?>