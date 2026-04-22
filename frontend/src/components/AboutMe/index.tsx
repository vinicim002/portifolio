import perfilImg from '../../assets/perfil.jpeg';

export function AboutMe() {
  return (
    <>
      <div className='aboutMeContainer mx-56 px-8 py-20 flex flex-row items-start gap-10'>
        <div className='imgAboutContent aspect-square rounded-3xl overflow-hidden glass p-2'>
          <img
            src={perfilImg}
            alt=''
            className='w-full h-full object-cover rounded-2xl grayscale hover:grayscale-0 transition-all duration-500'
          />
        </div>

        <div className='flex flex-col'>
          <div className='aboutMeContent flex flex-col items-start gap-6 mb-10'>
            <h2 className='text-7xl font-bold text-[#f8fafc]]'>Sobre mim</h2>
            <p className='text-text-dim text-base'>
              Lorem ipsum dolor sit amet consectetur adipisicing elit. Adipisci,
              voluptate. Doloribus consequatur, voluptate quisquam corporis
              doloremque deleniti, magnam cumque, neque voluptate quisquam
              corporis doloremque deleniti, magnam cumque, neque
            </p>
          </div>

          <div className='informatitionAbouteMe'>
            <div className='flex items-center justify-between gap-3.5 mb-4'>
              {/* Transformar em componente */}
              <div className='numberProjects glass-dark w-1/2 p-6 rounded-lg flex flex-col items-start gap-2'>
                <h3 className='text-xs text-text-dim'>PROJECTS</h3>
                <p className='font-bold text-5xl'>50+</p>
              </div>
              <div className='numberCommits glass-dark w-1/2 p-6 rounded-lg flex flex-col items-start gap-2'>
                <h3 className='text-xs text-text-dim'>COMMITS</h3>
                <p className='font-bold text-5xl'>10k+</p>
              </div>
            </div>
            <div className='myMission glass-dark p-6 rounded-lg'>
              <p>
                Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ea
                cupiditate harum autem vero repellat eaque, illo velit error
                quia beatae! Provident quis, iusto exercitationem aut modi ad
                excepturi aliquam aspernatur.
              </p>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
