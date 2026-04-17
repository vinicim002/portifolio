export function Hero() {
  return (
    <>
      <div className='heroContainer pb-20 pt-32 px-8'>
        <div className='heroCotent'>
          <div className='hero-badge bg-blue-950 text-white px-4 py-1.5 rounded-full flex items-center gap-2 w-max mb-4'>
            <span className='badge-dot'></span>
            Disponível para projetos
          </div>

          <h1 className='hero-name mb-4'>
            <span className='name-plain font-bold text-6xl'>
              Olá, eu sou
              <br />
            </span>
            <span className='name-grad font-bold text-6xl'>Vinicius Vila Nova</span>
          </h1>

          <p className="mb-10 font-normal text-xl">
            Desenvolvedor Full Stack apaixonado por criar experiências digitais elegantes com Java, Spring Boot e React.
          </p>

          <div className="hero-btns flex flex-row items-center gap-3.5">
            <button className="bg-blue-950 inline-flex gap-2 py-3 px-7 rounded-full text-white">Ver projetos</button>
            <button className="bg-blue-600 inline-flex gap-2 py-3 px-7 rounded-full text-white">Entrar em contato</button>
          </div>

          <div className="hero-scroll-hint mt-20">
            <div className="scroll-pill">
                <div className="scroll-dot"></div>
            </div>
            <span className="scroll-label">Scroll para explorar</span>
          </div>
        </div>
      </div>
    </>
  );
}
