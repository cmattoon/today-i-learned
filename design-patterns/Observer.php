<?php
/**
 * Observer pattern.
 *
 *
 */
interface Subject {
    public function register():void;
    public function unregister():void;
    public function notifyObserver():void;
}

interface Object {
    public function update(array $prices):void;
}

class StockGrabber implements Subject {
    private $observers = array();

    public function __construct() {

    }
}