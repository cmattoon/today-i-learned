<?php
/**
 * Builder Pattern
 *
 * - Create objects made from a bunch of other objects.
 * - Creation is independent from main object.
 * - Only Builder knows the specifics of building object
 */
interface RobotPlan {
    public function setRobotHead(string $s);
    public function setRobotTorso(string $s);
    public function setRobotArms(string $s);
    public function setRobotLegs(string $s);
}

interface RobotBuilder {
    public function buildRobotHead();
    public function buildRobotTorso();
    public function buildRobotArms();
    public function buildRobotLegs();
    public function getRobot();
}

class Robot implements RobotPlan {
    private $head = '';
    private $torso = '';
    private $arms = '';
    private $legs = '';

    public function __get(string $prop): string {
        if (property_exists($this, $prop)) {
            return $this->{$prop};
        }
        return '';
    }

    public function setRobotHead(string $head):void {
        $this->head = $head;
    }

    public function setRobotTorso(string $torso):void {
        $this->torso = $torso;
    }

    public function setRobotArms(string $arms):void {
        $this->arms = $arms;
    }

    public function setRobotLegs(string $legs):void {
        $this->legs = $legs;
    }
}

class OldRobotBuilder implements RobotBuilder {
    private $robot = null;

    public function __construct() {
        $this->robot = new Robot();
    }

    public function buildRobotHead(){
        $this->robot->setRobotHead("Tin Head");
    }

    public function buildRobotTorso(){
        $this->robot->setRobotTorso("Tin Torso");
    }

    public function buildRobotArms(){
        $this->robot->setRobotArms("Blowtorch");
    }

    public function buildRobotLegs(){
        $this->robot->setRobotLegs("Rollerblades");
    }

    public function getRobot(){
        return $this->robot;
    }
}

/**
 * Director/Engineer
 */
class RobotEngineer {
    private $builder = null;

    public function __construct(RobotBuilder $builder) {
        $this->builder = $builder;
    }

    public function getRobot():Robot {
        return $this->builder->getRobot();
    }

    public function makeRobot() {
        $this->builder->buildRobotHead();
        $this->builder->buildRobotTorso();
        $this->builder->buildRobotArms();
        $this->builder->buildRobotLegs();
    }
}

if (empty(debug_backtrace())) {

    $builder = new OldRobotBuilder();

    $engineer = new RobotEngineer($builder);

    $engineer->makeRobot();

    $robot1 = $engineer->getRobot();

    var_dump($robot1);
}