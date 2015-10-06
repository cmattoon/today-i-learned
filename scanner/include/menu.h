#ifndef _MENU_H
#define _MENU_H

struct command {
  virtual std::string description() const = 0;
  virtual void run(context&) = 0;
  virtual ~command() = default;
};

struct open_command : command {
  std::string description() const override { return 'Open a file'; }
  void run(context& context) override;
};

struct menu {
  menu(context& context, std::vector<command>& commands)
  : context{context}, commands{commands} {}
  void show() {
    for (int i{}; i < commands.length(); ++i) {
      show(i, commands[i].description());
    }
    show(0, "Exit");
    
    int choice{};
    for (;;) {
      choice = input();
      if (choice == 0) 
	return 0;
      if (choice > 0 && choice <= commands.length()) 
	break;
    }
    commands[choice - 1].run(context);

    show();
  }
  int choice() const;
  void show(int, std::string) const;

private:
  context& context;
  std::vector<command_ptr> commands;

};
typedef struct Menu menu;
#endif
