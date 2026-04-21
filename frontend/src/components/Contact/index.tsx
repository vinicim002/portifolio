import { z } from 'zod';
import { zodResolver } from '@hookform/resolvers/zod';
import { useForm } from 'react-hook-form';
import { useState } from 'react';
import { sendMessage } from '../../services/messageService';
import { ContactInfo } from '../ContactInfo';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEnvelope } from '@fortawesome/free-solid-svg-icons';
import { faSquareLinkedin } from '@fortawesome/free-brands-svg-icons';
import { faGithub } from '@fortawesome/free-brands-svg-icons';

const contactSchema = z.object({
  name: z.string().min(2, 'O nome deve conter pelo menos 2 caracteres'),
  email: z.string().email('Email inválido'),
  subject: z.string().min(5, 'O assunto deve conter pelo menos 5 caracteres'),
  body: z.string().min(10, 'A mensagem deve conter pelo menos 10 caracteres'),
});

type ContactFormData = z.infer<typeof contactSchema>;

export function Contact() {
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm<ContactFormData>({
    resolver: zodResolver(contactSchema),
  });

  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState(false);
  const [error, setError] = useState('');

  async function onSubmit(data: ContactFormData) {
    setLoading(true);
    setError('');
    try {
      await sendMessage(data);
      setSuccess(true);
      reset();
    } catch {
      setError('Erro ao enviar mensagem. Tente novamente.');
    } finally {
      setLoading(false);
    }
  }

  return (
    <>
      <div className='contactContainer mx-56 px-8 py-20 flex flex-row justify-between items-center gap-12'>
        <div className='contactContent flex flex-col gap-12'>
          <h2 className='text-7xl font-bold'>Contato</h2>
          <p className='text-text-dim text-base'>
            Interessado em escalar sua próxima ideia?
          </p>
          <div className='contactInfo flex flex-col gap-8'>
            <ContactInfo
              title={'Email'}
              description={'viniciusvilanova7@gmail.com'}
              icon={<FontAwesomeIcon icon={faEnvelope} className='text-3xl' />}
            />
            <ContactInfo
              title={'Github'}
              description={'github.com/vinicim002'}
              icon={<FontAwesomeIcon icon={faGithub} className='text-3xl' />}
            />
            <ContactInfo
              title={'Linkedin'}
              description={'linkedin.com/in/viniciusvila'}
              icon={
                <FontAwesomeIcon icon={faSquareLinkedin} className='text-3xl' />
              }
            />
          </div>
        </div>

        <form
          onSubmit={handleSubmit(onSubmit)}
          className='contactForm flex flex-col gap-8 glass p-8 rounded-xl border-border-glass'
        >
          <div className='contactFormInfoPesoal flex flex-row gap-8'>
            <label
              htmlFor='name'
              className='flex flex-col gap-2 text-text-dim text-xs'
            >
              Nome Completo
              <input
                type='text'
                id='name'
                placeholder='Seu nome'
                className='p-2 glass border-border-glass rounded-xl text-base'
                {...register('name')}
              />
              {errors.name && (
                <span className='text-red-400 text-xs'>
                  {errors.name.message}
                </span>
              )}
            </label>

            <label
              htmlFor='email'
              className='flex flex-col gap-2 text-text-dim text-xs'
            >
              Email
              <input
                type='text'
                id='email'
                placeholder='seu@gmail.com'
                className='p-2 glass border-border-glass rounded-xl text-base'
                {...register('email')}
              />
              {errors.email && (
                <span className='text-red-400 text-xs'>
                  {errors.email.message}
                </span>
              )}
            </label>
          </div>

          <label
            htmlFor='subject'
            className='flex flex-col gap-2 text-text-dim text-xs'
          >
            Assunto
            <input
              type='text'
              id='subject'
              placeholder='Assunto da mensagem'
              className='p-2 glass border-border-glass rounded-xl text-base'
              {...register('subject')}
            />
            {errors.subject && (
              <span className='text-red-400 text-xs'>
                {errors.subject.message}
              </span>
            )}
          </label>

          <label
            htmlFor='body'
            className='flex flex-col gap-2 text-text-dim text-xs'
          >
            Mensagem
            <textarea
              id='body'
              cols={30}
              rows={10}
              placeholder='Escreva sua mensagem aqui'
              className='p-2 glass border-border-glass rounded-xl text-base'
              {...register('body')}
            ></textarea>
            {errors.body && (
              <span className='text-red-400 text-xs'>
                {errors.body.message}
              </span>
            )}
          </label>

          {success && (
            <p className='text-green-400 text-sm'>
              Mensagem enviada com sucesso!
            </p>
          )}
          {error && <p className='text-red-400 text-sm'>{error}</p>}

          <button
            type='submit'
            disabled={loading}
            className='bg-[#f8fafc] text-bg p-4 rounded-xl font-bold cursor-pointer'
          >
            {loading ? 'Enviando...' : 'Enviar mensagem 🚀'}
          </button>
        </form>
      </div>
    </>
  );
}
