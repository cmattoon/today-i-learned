<?php
/**
 * Factory Pattern Demo (PHP 7.1)
 *
 * * Returns one of several possible classes that share a
 *   common super class
 *
 * * Class is chosen at runtime
 */
abstract class EnemyShip {
    protected $name = '';
    protected $damage = 0;
    protected $speed = 0;

    public function getName():string {
        return $this->name;
    }

    public function setName(string $name):void {
        $this->name = $name;
    }

    public function getSpeed():int {
        return $this->speed;
    }

    public function setSpeed(int $speed):void {
        $this->speed = $speed;
    }

    public function shoot() {
        echo "Does {$this->damage} damage" . PHP_EOL;
    }
}

class RocketEnemyShip extends EnemyShip {
    public function __construct() {
        $this->name = "Rocket";
        $this->damage = 10;
    }
}

class UFOEnemyShip extends EnemyShip {
    public function __construct() {
        $this->name = "UFO";
        $this->damage = 20;
    }
}

class BigUFOEnemyShip extends UFOEnemyShip {
    public function __construct() {
        $this->name = "Big UFO";
        $this->damage = 30;
    }
}

/**
 * Factory pattern begins here.
 * Note that if additional types are added, we
 * only have to modify code in this method.
 */
class EnemyShipFactory {
    const TYPE_ROCKET = 1;
    const TYPE_UFO = 2;
    const TYPE_BIG_UFO = 3;

    public static function create(int $type):EnemyShip {
        switch ($type) {
            case self::TYPE_ROCKET:
                return new RocketEnemyShip();

            case self::TYPE_UFO:
                return new UFOEnemyShip();

            case self::TYPE_BIG_UFO:
                return new BigUFOEnemyShip();
        }
    }
}

if (empty(debug_backtrace())) {
    $ship = EnemyShipFactory::create(EnemyShipFactory::TYPE_ROCKET);
    echo $ship->getName() . PHP_EOL;
}