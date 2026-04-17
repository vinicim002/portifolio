export function Navbar() {
  return (
    <>
      <nav className='navbarContainer flex flex-row items-center justify-between px-8 py-3 bg-blue-950 text-white h-16'>
        <div className="logoNavbar">&lt;Logo&gt;</div>
        <div className="menuContainer">
            <ul className="menuContent flex flex-row items-center justify-between gap-5">
                <li><a href="#">Sobre</a></li>
                <li><a href="#">Skills</a></li>
                <li><a href="#">Projetos</a></li>
                <li><a href="#">Contato</a></li>
            </ul>
        </div>
      </nav>
    </>
  );
}
