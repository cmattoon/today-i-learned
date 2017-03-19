<?php
/**
 * Programming Design Patterns in PHP 7.1
 *
 * Principles:
 *  - Program to an interface, not an implementation
 *  - Favor composition over inheritance
 */
class Vehicle {
    protected $make = '';
    protected $model = '';

    protected static $instance = null;

    public function __construct(string $make, string $model) {
        $this->make = trim($make);
        $this->model = trim($model);
    }

    public static function getInstance(): Vehicle {
        if (!self::$instance) {
            self::$instance = VehicleFactory::create();
        }
        return self::$instance;
    }
}

/**
 * Factory pattern
 *
 * - Creates the object without exposing creation logic.
 */
class VehicleFactory {

    /**
     * If the Vehicle class changes later, we only need to update
     * code here.
     *
     * @param string $make
     * @param string $model
     * @return Vehicle
     */
    public static function create(string $make, string $model): Vehicle {
        return new Vehicle($make, $model);
    }
}

/**
 * Strategy/Policy pattern.
 *
 * Allows definition of multiple algorithms and let
 * the application pass the correct one.
 */
interface PaymentMethod {
    public function pay(float $amount):bool;
}

class CreditCard implements PaymentMethod {

    public function pay(float $amount):bool {
        // Do stuff
        return true;
    }
}

class Cash implements PaymentMethod {
    public function pay(float $amount):bool {
        // Do stuff
        return true;
    }
}