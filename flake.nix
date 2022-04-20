{
  description = "Invoice Separation project";
  inputs = { 
    flake-utils.url = "github:numtide/flake-utils";
    nixpkgs.url = "github:nixos/nixpkgs/554d2d8aa25b6e583575459c297ec23750adb6cb";
  };

  outputs = { self, nixpkgs, flake-utils }:
    flake-utils.lib.eachDefaultSystem
      (system:
        let
          pkgs = import nixpkgs { inherit system; config = { allowUnfree = true; }; };
        in
        {
            devShell = pkgs.mkShell {
              buildInputs = with pkgs; [
                openjdk11
                swagger-codegen
              ];
            };
          }
      );
}

