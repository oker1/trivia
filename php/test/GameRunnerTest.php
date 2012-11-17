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

    public function recording()
    {
        for ($i = 0 ; $i <= 1000; $i++) {
            $this->recordOutput($i);
        }
    }
}

?>