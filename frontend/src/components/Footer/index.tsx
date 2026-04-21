import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faInstagram } from '@fortawesome/free-brands-svg-icons';

export function Footer() {
  return (
    <>
      <div className='footerContainer mx-56 px-8 pb-8 flex flex-row justify-between items-center'>
        <div className=''>
          <div className='nomeComLogo'>
            <img src='' alt='' />
            <p className='text-xl font-bold text-[#f8fafc]'>Vinicius</p>
          </div>
          <p className='text-text-dim text-base font-semibold'>
            Crafting premium digital experiences with precision and speed.
          </p>
        </div>

        <div className='redesSociais'>
          <a href=''>
            <FontAwesomeIcon icon={faInstagram} />
          </a>
        </div>

        <div className='copy'>
          <p className='text-base text-[#f8fafc] font-normal'>
            @ year Vinicius Vila Nova. Todos direitos reservados.
          </p>
        </div>
      </div>
    </>
  );
}
