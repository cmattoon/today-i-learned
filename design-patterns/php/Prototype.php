<?php
/**
 * Prototype Pattern
 *
 * - Create new instances by cloning.
 * - Adding any subclass of a known super class at runtime
 * - When there are numerous potential classes that you only want
 *   to use if needed at runtime
 * - Reduces need to create multiple subclasses
 */

abstract class Animal {
    public $name = '';
    public $species = '';
    abstract function __clone();
}

class Sheep extends Animal {
    public function __construct() { $this->species = 'sheep'; }
    public function __clone() {
        $this->name = 'Not Set';
    }
}

$dolly = new Sheep();
$dolly->name = 'Dolly';

$sally = clone($dolly);


var_dump($dolly);
var_dump($sally);

