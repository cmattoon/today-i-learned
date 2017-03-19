<?php
/**
 * Singleton Pattern
 *
 * Used to enforce a single instance.
 *
 */

/**
 * There should only be one Foo object
 */
class Foo {

    protected $id = 0;

    protected static $ctr = 0;

    protected static $instance = null;

    /**
     * Use this method to get the instance.
     * @return Foo
     */
    public static function get():Foo {
        if (empty(self::$instance)) {
            self::$instance = new Foo();
        }
        return self::$instance;
    }

    /**
     * Constructor.
     * Counter for illustrative purposes.
     */
    public function __construct() {
        $this->id = ++self::$ctr;
    }

    public function getId():int {
        return $this->id;
    }
}

$foo1 = Foo::get();
echo "First foo ID: {$foo1->getId()}" . PHP_EOL;

$foo3 = new Foo();
echo "New foo (bad): {$foo3->getId()}" . PHP_EOL;

$foo2 = Foo::get();
echo "Original Foo: {$foo2->getId()}" . PHP_EOL;

