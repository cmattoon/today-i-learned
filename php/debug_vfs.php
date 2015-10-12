<?php
use \org\bovigo\vfs;
/**
 * A quick debugging function to pretty-print a mock VFS file tree.
 * Ignores dotfiles, among other things. Included test case for orginal context only.
 *
 * @see http://vfs.bovigo.org/
 * @see https://github.com/mikey179/vfsStream
 */

/*
class DebugVFSTest extends PHPUnit_Framework_TestCase {

    public function testSomething() {
	vfs\vfsStreamWrapper::register(); // Register the stream                                                             
        
        $images_directory = "/path/to/assets/images";
        $root = vfs\vfsStream::setup();
        $parent = $root;

	// mkdir -p
        foreach (explode('/', $images_directory) as $dir) {
            if ($dir) {
                $current = vfs\vfsStream::newDirectory($dir);
                $parent->addChild($current);
                $parent = $current;
            }
        }

	// Add some files...
        for ($i = 0; $i < 20; ++$i) {
            $img = vfs\vfsStream::newFile(str_pad($i, 3, '0', STR_PAD_LEFT) . '.jpg');
            $parent->addChild($img);
        }
	
	// Pretty-print the directory tree
        debug_vfs($root);
    }
}
*/

function debug_vfs($root, $depth = 1) {
    if (!$root->hasChildren()) {
        echo "Empty root!";
        return;
    }

    // Start output on a new row
    if ($depth == 1) echo "\n";

    $iter = $root->getIterator();
    $indent = str_repeat(" ", $depth);

    for ($iter->rewind(); $iter->valid(); $iter->next()) {
        $current = $iter->current();

        switch ($current->getType()) {
            case vfs\vfsStreamContent::TYPE_FILE:
                echo $indent . "|--\033[36m" . basename($current->path()) . "\033[0m";
                echo "   [" . $current->url() . "]\n";
                break;                                                                                                       

            case vfs\vfsStreamContent::TYPE_DIR:
                if ($current->isDot()) continue;
                echo $indent . "[+] \033[33m" . basename($current->path()) . "\033[0m";
                echo "        " . $current->url() . "\n";
                if ($current->hasChildren()) {
                    debug_vfs($current, ++$depth);
                }
                break;
        }
    }
}